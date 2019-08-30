import React, {Component} from 'react';
import TicketService from '../../services/TicketingService'

export default class LoginContainer extends Component {
    constructor(props) {
        super(props);
        this.ticketService = new TicketService();
        this.state = {
            Username: '',
            Password: '',
        };
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    onChange(e) {
        this.setState({
            [e.target.name]: e.target.value
        });
    }

    onSubmit(e) {
        e.preventDefault();
        const loginRequest = {
            Username: this.state.Username,
            Password: this.state.Password
        };
        this.ticketService.logUser(loginRequest).then(() => {

        }).catch(err => {
            console.log(err)
        })
    }

    render() {
        return <div className="container-contact100">
            <div className="wrap-contact100">
                <form className="contact100-form validate-form" onSubmit={this.onSubmit}>
                    <span className="contact100-form-title">
					Log In!
				</span>


                    <div className="wrap-input100 validate-input" data-validate="Name is required">
                        <span className="label-input100">Username</span>
                        <input className="input100" type="text" required={true} value={this.state.Username}
                               onChange={this.onChange} name="Username"/>
                        <span className="focus-input100"/>
                    </div>

                    <div className="wrap-input100 validate-input" data-validate="Name is required">
                        <span className="label-input100">Password</span>
                        <input className="input100" type="password" required={true} value={this.state.Password}
                               onChange={this.onChange} name="Password"/>
                        <span className="focus-input100"/>
                    </div>

                    <div className="container-contact100-form-btn">
                        <div className="wrap-contact100-form-btn">
                            <div className="contact100-form-bgbtn"/>
                            <button className="contact100-form-btn">
							<span>
                                Log In
                            <input type="submit" value=""/>
								<i className="fa fa-long-arrow-right m-l-7" aria-hidden="true"/>
							</span>
                            </button>
                        </div>
                    </div>

                    <div className="row mt-5">
                        <div className="col-lg-6 mt-1">
                            <button onClick={() => {
                                window.location.href = "/registerStudent";
                            }} className="btn btn-secondary btn-block" style={{width: '250px'}}>New User ? Register
                                Here
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>;
    }
}