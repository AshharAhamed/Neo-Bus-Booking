import React, {Component} from 'react';
import PassengerService from '../../services/PassengerService'
import UserService from "../../services/UserService";
import QueueAnim from "rc-queue-anim";
import OneCard from "./list/card/OneCard";
import Ripples from "react-ripples";
import {Button} from "react-bootstrap";

export default class PassengerAccount extends Component {
    constructor(props) {
        super(props);
        this.state = {
            cardNo: '',
            creditBalance: '',
            loanAmount: '',
            cardList: ''
        };
        this.passengerService = new PassengerService();
        this.userService = new UserService();
        this.onChange = this.onChange.bind(this);
        this.loadPassengerAccount(this.userService.username);
    }

    onChange(e) {
        this.setState({
            [e.target.name]: e.target.value,
        });
    }

    loadPassengerAccount(cardNo) {
        this.passengerService.getAccount(cardNo).then(response => {
            this.setState({
                cardNo: response.data.cardNo,
                creditBalance: response.data.creditBalance,
                loanAmount: response.data.loanAmount,
                cardList: response.data.cardList
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

    tabRow() {
        if(this.state.cardList !== null && this.state.cardList !== ''){
            return this.state.cardList.map(function (object, i) {
                return <OneCard obj={object} key={i}/>;
            });
        }
    }

    render() {
        return (
            <div>
                <div className="container p-2" style={{paddingBottom: '500px'}}>
                    <form onSubmit={this.onSubmit}>
                        <QueueAnim duration="1000" interval="400">
                            <span className="contact100-form-title float-lg-left">My Account</span>
                            <br/><br/><br/><br/>

                            <div key="1" className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100">Travel Card No : </span>
                                <input className="input100" type="text" required={true} value={this.state.cardNo}
                                       onChange={this.onChange} name="cardNo" readOnly/>
                                <span className="focus-input100"/>
                            </div>

                            <div key="2" className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100">Credit Balance : </span>
                                <input className="input100" type="text" required={true} value={this.state.creditBalance}
                                       onChange={this.onChange} name="creditBalance" readOnly/>
                                <span className="focus-input100"/>
                            </div>

                            <div key="3" className="wrap-input100 validate-input" data-validate="Name is required">
                                <span className="label-input100">Loan Amount : </span>
                                <input className="input100" type="text" required={true} value={this.state.loanAmount}
                                       onChange={this.onChange} name="loanAmount" readOnly/>
                                <span className="focus-input100"/>
                            </div>

                            <div>
                                My Cards

                                <div key="1" className="col-lg mt-3" style={{marginBottom: "10px"}}>
                                    <Ripples>
                                        <Button className="btn btn-success" onClick={() => {
                                            window.location.href = "/addPassengerCard";
                                        }}>Add Card <i className="fa fa-credit-card"/></Button>
                                    </Ripples>
                                </div>

                                <table className="table  table-bordered table-hover table-striped ">
                                    <thead>
                                    <tr>
                                        <th className="text-center"><QueueAnim><div key="1">Card No</div></QueueAnim></th>
                                        <th className="text-center"><QueueAnim><div key="1">Action</div></QueueAnim></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {this.tabRow()}
                                    </tbody>
                                </table>
                            </div>

                        </QueueAnim>
                    </form>
                </div>
            </div>
        );
    }
}