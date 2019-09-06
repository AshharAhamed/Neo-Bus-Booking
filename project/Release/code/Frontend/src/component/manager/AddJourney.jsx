import React, {Component} from 'react';
import ManagerService from "../../services/ManagerService";
import UserService from "../../services/UserService";
import Select from 'react-select';
import QueueAnim from "rc-queue-anim";

export default class AddJourney extends Component {
    constructor(props) {
        super(props);
        this.state = {
            busNo: '',
            routeList: [],
            routeOptions : [],
            selectedRoute : null
        };
        this.managerService = new ManagerService();
        this.userService = new UserService();
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
        this.clearForm = this.clearForm.bind(this);
        this.loadRouteList();
    }

    handleChange = selectedRoute => {
        this.setState({ selectedRoute });
    };

    onChange(e) {
        this.setState({
            [e.target.name]: e.target.value,
        });
    }

    clearForm(e) {
        this.setState({
            busNo: ''
        });
    }

    loadRouteList() {
        this.managerService.getAllRouteNames().then( response => {
            this.setState( { routeList : response.data.routes});
            this.setState({routeOptions : this.tabRow()});
        }).catch(error => {
            console.log(error)
        })
    }

    tabRow() {
        if(this.state.routeList !== null && this.state.routeList !== ''){
            return this.state.routeList.map(function (x, y) {
                const myVal = { value : '' + x + '' , label: + '' + x + ''};
                return myVal;
            });
        }
    }

    onSubmit(e) {
        e.preventDefault();
        this.managerService.startJourney( this.state.selectedRoute.value, {
            "busNo" : this.state.busNo
        }).then( response => {
            alert(response.data)
        }).catch( error => {
            console.log(error)
        })
    }
    // 2019-09-18T10:00:00.000+00:00
    // 2019-9-5T16:17:53

     getCurrentDateTime() {
        var tempDate = new Date();
        var date = tempDate.getFullYear() + '-' + (tempDate.getMonth()+1) + '-' + tempDate.getDate() +'T'+ tempDate.getHours()+':'+ tempDate.getMinutes()+':'+ tempDate.getSeconds() + '.000+00:00';
        return date;
    }

    render() {
        const { selectedRoute } = this.state;

        return (
            <div>

                <div className="container p-2" style={{paddingBottom: '500px'}}>
                    <form onSubmit={this.onSubmit}>
                        <QueueAnim duration="1000" interval="400">
                            <span className="contact100-form-title float-lg-left">Add Journey</span>
                            <br/><br/><br/><br/>

                            <div key="1" className="wrap-input100 validate-input float-sm-left" data-validate="Name is required">
                                <span className="label-input100">Bus No : </span>
                                <input className="input100" type="text" required={true} value={this.state.busNo}
                                       onChange={this.onChange} name="busNo" placeholder="NB-5788"/>
                                <span className="focus-input100"/>
                            </div>

                            <div className="input100-select" style={{marginLeft : "800px", marginTop : "10px"}}>
                                <span className="label-input100">Select Route</span><br/>
                                <div>
                                    <Select onChange={this.handleChange}  value={selectedRoute} options={this.state.routeOptions} />
                                </div>
                            </div>
                            <br/><br/><br/><br/>

                            <div className="container-contact100-form-btn float-left">
                                <div className="wrap-contact100-form-btn">
                                    <div className="contact100-form-bgbtn"/>
                                    <button className="contact100-form-btn">
							<span>
                                Top Up  <i className="fa fa-paper-plane"/>
                            <input type="submit" value=""/>
							</span>
                                    </button>
                                </div>
                            </div>

                            <div style={{marginTop: "20px", marginLeft: "200px"}}>
                                <button style={{width: "100px"}} className="btn btn-secondary btn-block"
                                        onClick={this.clearForm}>Clear <i
                                    className="fa fa-eraser"/>
                                </button>
                            </div>
                        </QueueAnim>
                    </form>
                </div>
            </div>
        );
    }
}