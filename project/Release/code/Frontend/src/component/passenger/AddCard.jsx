import React, {Component} from 'react';
import PassengerService from '../../services/PassengerService'
import UserService from "../../services/UserService";
import {Link} from "react-router-dom";
import QueueAnim from "rc-queue-anim";

export default class AddPassengerCardContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            cardNo: '',
            expiryDate: '',
            ccNo: ''
        };
        this.userService = new UserService();
        this.passengerService = new PassengerService();
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
            cardNo: '',
            expiryDate: '',
            ccNo: ''
        });
    }


    onSubmit(e) {
        e.preventDefault();

        this.passengerService.addCard(this.userService.username, {
            "cardNo": this.state.cardNo,
            "expiryDate": this.state.expiryDate + '-00',
            "ccNo": this.state.ccNo
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
                    <div className="navbar-brand"><Link className="navbar-brand" to="/passengerAccount"><i
                        className="fa fa-arrow-circle-left" style={{margin: '12px'}}/>Back
                    </Link></div>
                </ul>
            </div>
            <br/><br/><br/><br/>
            <QueueAnim>
                <div key="1">
                    <form onSubmit={this.onSubmit} style={{marginLeft: "50px"}}>
				<span className="contact100-form-title float-lg-left">
					Add Card
				</span><br/><br/><br/><br/>
                        <div className="wrap-input100 validate-input" data-validate="Name is required">
                            <span className="label-input100 float-left">Card No</span>
                            <br/>
                            <input className="input100" type="number" required={true} value={this.state.cardNo}
                                   onChange={this.onChange} name="cardNo" placeholder="4567 9345 1234 5434"/>
                            <span className="focus-input100"/>
                        </div>

                        <div className="wrap-input100 validate-input" data-validate="Name is required">
                            <span className="label-input100  float-left">Expiry Date</span>
                            <input className="input100" type="month" required={true} value={this.state.expiryDate}
                                   onChange={this.onChange} name="expiryDate" placeholder="Asplund"/>
                            <span className="focus-input100"/>
                        </div>

                        <div className="wrap-input100 validate-input" data-validate="Name is required">
                            <span className="label-input100  float-left">CC No</span>
                            <input className="input100" type="text" required={true} value={this.state.ccNo}
                                   onChange={this.onChange} name="ccNo" placeholder="387"/>
                            <span className="focus-input100"/>
                        </div>

                        <div className="container-contact100-form-btn float-left">
                            <div className="wrap-contact100-form-btn">
                                <div className="contact100-form-bgbtn"/>
                                <button className="contact100-form-btn">
							<span>
                                Add  <i className="fa fa-paper-plane"/>
                            <input type="submit" value=""/>
							</span>
                                </button>
                            </div>
                        </div>

                        <div style={{marginTop: "48px", marginLeft: "200px"}}>
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