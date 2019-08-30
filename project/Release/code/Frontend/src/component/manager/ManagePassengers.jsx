import React, {Component} from 'react';
import PassengerList from './list/passenger/passengerList'
import {Button} from "react-bootstrap";
import Ripples from "react-ripples";
import QueueAnim from "rc-queue-anim";

export default class ManagePassenger extends Component {
    render() {
        return (
            <div>
                <QueueAnim duration="3000" interval="400">
                    <div key="1">
                        <Ripples>
                            <Button className="btn btn-success" onClick={() => {
                                window.location.href = "/addPassenger";
                            }}>Add Passenger <i className="fa fa-user-plus"/></Button></Ripples>
                    </div>
                    <div key="2">
                        <PassengerList/>
                    </div>
                </QueueAnim>
            </div>
        )
    };
}