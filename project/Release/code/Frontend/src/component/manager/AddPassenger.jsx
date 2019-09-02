import React, {Component} from 'react';
import ManagerService from '../../services/ManagerService'
import {Link} from "react-router-dom";
import QueueAnim from "rc-queue-anim";

export default class RegisterPassengerContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            FirstName: '',
            LastName: '',
            Email: '',
            Phone: '',
            DoB: '',
            Type: 'Local',
            CardNo: '',
            NIC: '',
            PassportID: ''
        };

        this.managerService = new ManagerService();
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
        this.clearForm = this.clearForm.bind(this);
    }

    onChange(e) {
        this.setState({
            [e.target.name]: e.target.value
        });
    }

    clearForm(e) {
        this.setState({
            FirstName: '',
            LastName: '',
            Email: '',
            Phone: '',
            DoB: '',
            Type: '',
            CardNo: '',
            NIC: '',
            PassportID: ''
        });
    }

    onSubmit(e) {
        e.preventDefault();

        this.managerService.addPassenger({
            "firstName": this.state.FirstName,
            "lastName": this.state.LastName,
            "email": this.state.Email,
            "contact": this.state.Phone,
            "dob": this.state.DoB,
            "type": this.state.Type,
            "cardNo": this.state.CardNo,
            "passport": this.state.PassportID,
            "nic": this.state.NIC,
            "creditBalance" : "100"
        }).then(response => {
            alert(response.data)
        }).catch(error => {
            console.log(error)
        })
    }

    render() {
        return <div>
            <div className="float-sm-left">
                <ul className="navbar-nav mr-auto">
                    <div className="navbar-brand"><Link className="navbar-brand" to="/managePassengers"><i
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
                            <span className="label-input100 float-left">First Name</span>
                            <br/>
                            <input className="input100" type="text" required={true} value={this.state.FirstName}
                                   onChange={this.onChange} name="FirstName" placeholder="Daniel"/>
                            <span className="focus-input100"/>
                        </div>

                        <div className="wrap-input100 validate-input" data-validate="Name is required">
                            <span className="label-input100  float-left">Last Name</span>
                            <input className="input100" type="text" required={true} value={this.state.LastName}
                                   onChange={this.onChange} name="LastName" placeholder="Asplund"/>
                            <span className="focus-input100"/>
                        </div>

                        <div className="input100-select">
                            <span className="label-input100">Select Type</span><br/>
                            <div>
                                <select id="PassType" name="Type" className="selection-2" onChange={this.onChange}
                                        value={this.state.Type}>
                                    <option value="Local">Local</option>
                                    <option value="Foreign">Foreign</option>
                                </select>
                            </div>
                        </div>
                        <br/>

                        <div className="wrap-input100 validate-input" data-validate="Name is required">
                            <span className="label-input100  float-left">Email</span>
                            <input className="input100" type="email" required={true} value={this.state.Email}
                                   onChange={this.onChange} name="Email" placeholder="danielasplund@gmail.com"/>
                            <span className="focus-input100"/>
                        </div>

                        <div className="wrap-input100 validate-input" data-validate="Name is required">
                            <span className="label-input100  float-left">Phone</span>
                            <input className="input100" type="number" required={true} value={this.state.Phone}
                                   onChange={this.onChange} name="Phone" placeholder="0712234567"/>
                            <span className="focus-input100"/>
                        </div>

                        <div className="wrap-input100 validate-input" data-validate="Name is required">
                            <span className="label-input100">Date of Birth</span>
                            <input className="input100" type="date" name="DoB" value={this.state.DoB}
                                   onChange={this.onChange} placeholder="Enter your Date of Birth" required={true}/>
                            <span className="focus-input100"/>
                        </div>

                        <div className="wrap-input100 validate-input" data-validate="Name is required">
                            <span className="label-input100  float-left">Card No</span>
                            <input className="input100" type="number" required={true} value={this.state.CardNo}
                                   onChange={this.onChange} name="CardNo" placeholder="3221"/>
                            <span className="focus-input100"/>
                        </div>

                        <div className={this.state.Type === 'Local' ? 'hiddenDivTag' : ''}>
                            <div className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100  float-left">Passport</span>
                                <input className="input100" type="text" required={this.state.Type === 'Foreign'} value={this.state.PassportID}
                                       onChange={this.onChange} name="PassportID" placeholder="681588907"/>
                                <span className="focus-input100"/>
                            </div>
                        </div>

                        <div className={this.state.Type === 'Foreign' ? 'hiddenDivTag' : ''}>
                            <div className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100  float-left">NIC</span>
                                <input className="input100" type="text" required={this.state.Type === 'Local'} value={this.state.NIC}
                                       onChange={this.onChange} name="NIC" placeholder="986543279V"/>
                                <span className="focus-input100"/>
                            </div>
                        </div>
                        <div className="container-contact100-form-btn float-left">
                            <div className="wrap-contact100-form-btn">
                                <div className="contact100-form-bgbtn"/>
                                <button className="contact100-form-btn">
							<span>
                                Register  <i className="fa fa-paper-plane"/>
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
        </div>;
    }
}