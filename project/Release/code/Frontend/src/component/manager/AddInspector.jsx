import React, {Component} from 'react';
import ManagerService from '../../services/ManagerService'
import {Link} from "react-router-dom";
import QueueAnim from "rc-queue-anim";
import Modal from "react-awesome-modal";

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
                "type": "Inspector"
            }).then(response => {
                alert(response.data)
            }).catch(error => {
                console.log(error)
            })
        }
    }

    render() {
        return <div>
            <div>
                <div className="float-sm-left">
                    <ul className="navbar-nav mr-auto">
                        <div className="navbar-brand"><Link className="navbar-brand" to="/manageInspectors"><i
                            className="fa fa-arrow-circle-left" style={{margin: '12px'}}/>Back
                        </Link></div>
                    </ul>
                </div>
                <br/><br/><br/><br/>
                <QueueAnim>
                    <div key="1">
                        <form onSubmit={this.onSubmit} style={{marginLeft: "50px"}}>
				<span className="contact100-form-title float-lg-left">
					Add Inspector
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

                            <div className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100  float-left">Username</span>
                                <input className="input100" type="text" required={true} value={this.state.Username}
                                       onChange={this.onChange} name="Username" placeholder="daasselk"/>
                                <span className="focus-input100"/>
                            </div>

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
                                <span className="label-input100  float-left">Password</span>
                                <input className="input100" type="password" required={true} name="Password"
                                       value={this.state.Password}
                                       onChange={this.onChange}/>
                                <span className="focus-input100"/>
                            </div>

                            <div className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100  float-left">Confirm Password</span>
                                <input className="input100" type="password" required={true} name="ConfirmPassword"
                                       value={this.state.ConfirmPassword}
                                       onChange={this.onChange}/>
                                <span className="focus-input100"/>
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

                            <div style={{marginTop : "48px", marginLeft : "300px"}}>
                                <button style={{width : "100px"}} className="btn btn-secondary btn-block" onClick={this.clearForm}>Clear <i
                                    className="fa fa-eraser"/>
                                </button>
                            </div>

                        </form>
                    </div>
                </QueueAnim>
            </div>
        </div>;
    }
}