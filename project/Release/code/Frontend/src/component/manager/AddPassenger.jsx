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
            Username: '',
            Email: '',
            Phone: '',
            Password: '',
            ConfirmPassword: ''
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
            Username: '',
            Email: '',
            Phone: '',
            Password: '',
            ConfirmPassword: ''
        });
    }

    onSubmit(e) {
        e.preventDefault();
        if (this.state.Password !== this.state.ConfirmPassword) {
            alert("Passwords do not Match !")
        } else {
            this.managerService.addUser({
                "firstName": this.state.FirstName,
                "lastName": this.state.LastName,
                "username": this.state.Username,
                "email": this.state.Email,
                "contact": this.state.Phone,
                "password": this.state.Password,
                "type": "Passenger"
            }).then(response => {
                alert(response.data)
            }).catch(error => {
                console.log(error)
            })
        }
    }

    render() {
        return <div className="container-contact100">
            <div className="wrap-contact100">
                <div className="form-inline my-2 my-lg-0">
                    <ul className="navbar-nav mr-auto">
                        <div className="navbar-brand"><Link className="navbar-brand" to="/managePassengers"><i
                            className="fa fa-arrow-circle-left" style={{margin: '12px'}}/>Back
                        </Link></div>
                    </ul>
                </div>
                <QueueAnim>
                    <div key="1">
                        <form className="contact100-form validate-form" onSubmit={this.onSubmit}>
				<span className="contact100-form-title">
					Add Passenger
				</span>
                            <div className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100">First Name</span>
                                <input className="input100" type="text" required={true} value={this.state.FirstName}
                                       onChange={this.onChange} name="FirstName" placeholder="Daniel"/>
                                <span className="focus-input100"/>
                            </div>

                            <div className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100">Last Name</span>
                                <input className="input100" type="text" required={true} value={this.state.LastName}
                                       onChange={this.onChange} name="LastName" placeholder="Asplund"/>
                                <span className="focus-input100"/>
                            </div>

                            <div className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100">Username</span>
                                <input className="input100" type="text" required={true} value={this.state.Username}
                                       onChange={this.onChange} name="Username" placeholder="daasselk"/>
                                <span className="focus-input100"/>
                            </div>

                            <div className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100">Email</span>
                                <input className="input100" type="email" required={true} value={this.state.Email}
                                       onChange={this.onChange} name="Email" placeholder="danielasplund@gmail.com"/>
                                <span className="focus-input100"/>
                            </div>

                            <div className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100">Phone</span>
                                <input className="input100" type="number" required={true} value={this.state.Phone}
                                       onChange={this.onChange} name="Phone" placeholder="0712234567"/>
                                <span className="focus-input100"/>
                            </div>

                            <div className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100">Password</span>
                                <input className="input100" type="password" required={true} name="Password"
                                       value={this.state.Password}
                                       onChange={this.onChange}/>
                                <span className="focus-input100"/>
                            </div>

                            <div className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100">Confirm Password</span>
                                <input className="input100" type="password" required={true} name="ConfirmPassword"
                                       value={this.state.ConfirmPassword}
                                       onChange={this.onChange}/>
                                <span className="focus-input100"/>
                            </div>

                            <div className="container-contact100-form-btn">
                                <div className="wrap-contact100-form-btn">
                                    <div className="contact100-form-bgbtn"/>
                                    <button className="contact100-form-btn">
							<span>
                                Register
                            <input type="submit" disabled={!this.state.isValid} value=""/>
								<i className="fa fa-long-arrow-right m-l-7" aria-hidden="true"/>
							</span>
                                    </button>
                                </div>
                            </div>

                            <div className="row mt-5">

                                <div className="col-lg-6 mt-1">
                                    <button className="btn btn-secondary btn-block" onClick={this.clearForm}>Clear
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </QueueAnim>
            </div>
        </div>;
    }
}