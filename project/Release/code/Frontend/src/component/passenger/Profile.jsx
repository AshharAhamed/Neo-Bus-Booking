import React, {Component} from 'react';
import PassengerService from '../../services/PassengerService'
import UserService from "../../services/UserService";
import QueueAnim from "rc-queue-anim";

export default class PassengerProfile extends Component {
    constructor(props) {
        super(props);
        this.state = {
            accountId: '',
            firstName: '',
            lastName: '',
            email: '',
            contact: '',
            dob: '',
            type: '',
            nic: '',
            passport: ''
        };
        this.passengerService = new PassengerService();
        this.userService = new UserService();
        this.onChange = this.onChange.bind(this);
        this.loadPassengerDetails(this.userService.username);
    }

    onChange(e) {
        this.setState({
            [e.target.name]: e.target.value,
        });
    }

    loadPassengerDetails(cardNo) {
        this.passengerService.getProfile(cardNo).then(response => {
            let oldDate = new Date(response.data.dob),
                month = '' + (oldDate.getMonth() + 1),
                day = '' + oldDate.getDate(),
                year = oldDate.getFullYear();
            if (month.length < 2)
                month = '0' + month;
            if (day.length < 2)
                day = '0' + day;
            const myDate = [year, month, day].join('-');
            this.setState({
                accountId: response.data.accountId,
                firstName: response.data.firstName,
                lastName: response.data.lastName,
                email: response.data.email,
                contact: response.data.contact,
                dob: myDate,
                nic: response.data.nic,
                passport: response.data.passport,
                type: response.data.type
            });
        }).catch(error => {
            console.log(error)
        })
    }

    onSubmit(e) {
        e.preventDefault();
        // this.SISService.modifyStudent(this.state.userName, {
        //     FirstName: this.state.firstName,
        //     LastName: this.state.lastName,
        //     Email: this.state.email,
        //     Mobile: this.state.mobile,
        //     DoB: this.state.dob,
        //     Faculty: this.state.faculty,
        //     Gender: this.state.gender
        // }).then(response => {
        //     console.log(response);
        //     alert('Your Profile has been Successfully Updated');
        // }).catch(error => {
        //     console.log(error);
        // });
    }


    render() {
        return (
            <div>
                <div className="container p-2" style={{paddingBottom: '500px'}}>
                    <form onSubmit={this.onSubmit}>
                        <QueueAnim duration="1000" interval="400">
                            <span className="contact100-form-title float-lg-left">My Profile</span>
                            <br/><br/><br/><br/>

                            <div key="1" className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100">Account ID : </span>
                                <input className="input100" type="text" required={true} value={this.state.accountId}
                                       onChange={this.onChange} name="accountId" readOnly/>
                                <span className="focus-input100"/>
                            </div>

                            <div key="2" className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100">First Name : </span>
                                <input className="input100" type="text" required={true} value={this.state.firstName}
                                       onChange={this.onChange} name="firstName" readOnly/>
                                <span className="focus-input100"/>
                            </div>

                            <div key="3" className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100">Last Name : </span>
                                <input className="input100" type="text" required={true} value={this.state.lastName}
                                       onChange={this.onChange} name="lastName" readOnly/>
                                <span className="focus-input100"/>
                            </div>

                            <div key="4" className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100">Email : </span>
                                <input className="input100" type="text" required={true} value={this.state.email}
                                       onChange={this.onChange} name="email" readOnly/>
                                <span className="focus-input100"/>
                            </div>

                            <div key="5" className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100">Mobile</span>
                                <input className="input100" type="number" required={true} value={this.state.contact}
                                       onChange={this.onChange} name="contact" readOnly/>
                                <span className="focus-input100"/>
                            </div>

                            <div key="6" className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100">Type : </span>
                                <input className="input100" type="text" required={true} value={this.state.type}
                                       onChange={this.onChange} name="Type" readOnly/>
                                <span className="focus-input100"/>
                            </div>

                            <div  key="7" className={this.state.type === 'Foreign' ? 'hiddenDivTag' : ''}>
                                <div className="wrap-input100 validate-input" data-validate="Name is required">
                                    <span className="label-input100">NIC</span>
                                    <input className="input100" type="text" required={true} value={this.state.nic}
                                           onChange={this.onChange} name="nic" readOnly/>
                                    <span className="focus-input100"/>
                                </div>
                            </div>

                            <div key="8" className={this.state.type === 'Local' ? 'hiddenDivTag' : ''}>
                                <div  className="wrap-input100 validate-input" data-validate="Name is required">
                                    <span className="label-input100">Passport</span>
                                    <input className="input100" type="text" required={true} value={this.state.passport}
                                           onChange={this.onChange} name="passport" readOnly/>
                                    <span className="focus-input100"/>
                                </div>
                            </div>

                            <div key="9" className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100">Date of Birth</span>
                                <input className="input100" type="date" required={true} name="dob"
                                       value={this.state.dob}
                                       onChange={this.onChange} placeholder="Enter your Date of Birth" readOnly/>
                                <span className="focus-input100"/>
                            </div>

                            <div key="10" className="col-lg mt-3">
                                <input type="submit" className="btn btn-info btn-block" value="Change"/>
                            </div>
                        </QueueAnim>
                    </form>
                </div>
            </div>
        );
    }
}