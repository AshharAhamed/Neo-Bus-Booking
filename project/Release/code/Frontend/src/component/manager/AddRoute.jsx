import React, {Component} from 'react';
import ManagerService from '../../services/ManagerService'
import QueueAnim from "rc-queue-anim";
import {Link} from "react-router-dom";
import Ripples from "react-ripples";

export default class AddRoute extends Component {
    constructor(props) {
        super(props);
        this.state = {
            routeName: '',
            routeNo: '',
            busHalts: '',
            busHaltArray: []
        };
        this.managerService = new ManagerService();
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
        this.clearForm = this.clearForm.bind(this);
        this.addBusStation = this.addBusStation.bind(this);
        this.handleRemove = this.handleRemove.bind(this);

    }

    onChange(e) {
        this.setState({
            [e.target.name]: e.target.value,
        });
    }

    clearForm(e) {
        this.setState({
            routeName: '',
            busHalts: ''
        });
    }

    onSubmit(e) {
        e.preventDefault();
        this.managerService.addRoute({
            "routeName": this.state.routeName,
            "routeNo": this.state.routeNo,
            "busHalts": this.state.busHaltArray
        }).then(response => {
            alert(response.data)
        }).catch(error => {
            console.log(error)
        })
    }

    addBusStation(e) {
        e.preventDefault();
        this.setState({busHaltArray: [...this.state.busHaltArray, ""]})
    }

    handleChange(e, index) {
        this.state.busHaltArray[index] = e.target.value;
        this.setState({busHaltArray: this.state.busHaltArray})
    }

    handleRemove(index) {
        this.state.busHaltArray.splice(index, 1)
        this.setState({busHaltArray: this.state.busHaltArray})
    }

    render() {
        const ColoredLine = ({ color }) => (
            <hr
                style={{
                    color: color,
                    backgroundColor: color,
                    width : "400px"
                }}
            />
        );

        return (
            <div>
                <div className="float-sm-left">
                    <ul className="navbar-nav mr-auto">
                        <div className="navbar-brand"><Link className="navbar-brand" to="/manageRoutes"><i
                            className="fa fa-arrow-circle-left" style={{margin: '12px'}}/>Back
                        </Link></div>
                    </ul>
                </div>
                <br/><br/><br/><br/>
                <QueueAnim>
                    <div key="1">
                        <form onSubmit={this.onSubmit} style={{marginLeft: "50px"}}>
				<span className="contact100-form-title float-lg-left">
					Add Passenger
				</span><br/><br/><br/><br/>
                            <div className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100 float-left">Route</span>
                                <br/>
                                <input className="input100" type="text" required={true} value={this.state.routeName}
                                       onChange={this.onChange} name="routeName" placeholder="Nugegoda-Borella"/>
                                <span className="focus-input100"/>
                            </div>

                            <div className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100 float-left">Route No</span>
                                <br/>
                                <input className="input100" type="text" required={true} value={this.state.routeNo}
                                       onChange={this.onChange} name="routeNo" placeholder="178"/>
                                <span className="focus-input100"/>
                            </div>

                            <div>
                                <span className="label-input100">Bus Halts</span><br/><br/>
                                {
                                    this.state.busHaltArray.map((busHalt, index) => {
                                        return (
                                            <div key={index}>
                                                <input type="text" style={{width: "400px"}} placeholder="Borella"
                                                       value={busHalt} onChange={(e) => this.handleChange(e, index)}/>
                                                <span/>
                                                <Ripples>
                                                    <button className="btn btn-danger"
                                                            onClick={(e) => this.handleRemove(index)}>Remove <i
                                                        className="fa fa-times"/></button>
                                                </Ripples>
                                                <ColoredLine color="black" />
                                            </div>
                                        )
                                    })
                                }

                                <div key="1" className="col-lg mt-3" style={{marginBottom: "10px"}}>
                                    <Ripples>
                                        <button className="btn btn-success" onClick={(e) => this.addBusStation(e)}>Add
                                            Bus Station <i className="fa fa-bus"/></button>
                                    </Ripples>
                                </div>
                            </div>

                            <div className="container-contact100-form-btn float-left">
                                <div className="wrap-contact100-form-btn">
                                    <div className="contact100-form-bgbtn"/>
                                    <button className="contact100-form-btn">
							<span>
                                Add Bus Route  <i className="fa fa-paper-plane"/>
                            <input type="submit" value=""/>
							</span>
                                    </button>
                                </div>
                            </div>

                            <div style={{marginTop: "48px", marginLeft: "300px"}}>
                                <button style={{width: "100px"}} className="btn btn-secondary btn-block"
                                        onClick={this.clearForm}>Clear <i
                                    className="fa fa-eraser"/>
                                </button>
                            </div>
                        </form>
                    </div>
                </QueueAnim>
            </div>
        );
    }
}