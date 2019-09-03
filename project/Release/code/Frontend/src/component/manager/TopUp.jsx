import React, {Component} from 'react';
import ManagerService from '../../services/ManagerService'
import QueueAnim from "rc-queue-anim";

export default class AdminTopUp extends Component {
    constructor(props) {
        super(props);
        this.state = {
            travelCardID : '',
            amount: ''
        };
        this.managerService = new ManagerService();
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
        this.clearForm = this.clearForm.bind(this);
    }

    onChange(e) {
        this.setState({
            [e.target.name]: e.target.value,
        });
    }

    clearForm(e) {
        this.setState({
            travelCardID : '',
            amount: ''
        });
    }

    onSubmit(e) {
        e.preventDefault();
        this.managerService.topUpPassenger(this.state.travelCardID, {
            "amount" : this.state.amount
        }).then( response => {
            alert(response.data)
        }).catch( error => {
            console.log(error)
        })
    }

    render() {
        return (
            <div>
                <div className="container p-2" style={{paddingBottom: '500px'}}>
                    <form onSubmit={this.onSubmit}>
                        <QueueAnim duration="1000" interval="400">

                            <div key="1" className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100">Travel Card No : </span>
                                <input className="input100" type="text" required={true} value={this.state.travelCardID}
                                       onChange={this.onChange} name="travelCardID" placeholder="1243"/>
                                <span className="focus-input100"/>
                            </div>

                            <div key="2" className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100">Amount : </span>
                                <input className="input100" type="number" required={true} value={this.state.amount}
                                       onChange={this.onChange} name="amount" min="0" placeholder="Rs. 100"/>
                                <span className="focus-input100"/>
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