import React, {Component} from 'react';
import PassengerList from './list/passenger/passengerList'
import {Button} from "react-bootstrap";
import Ripples from "react-ripples";
import QueueAnim from "rc-queue-anim";

export default class ManagePassenger extends Component {
    render() {
        return (
            <div>
                <h4 style={{marginTop: "10px"}}>Registered Passengers </h4>
                <QueueAnim duration="3000" interval="400">
                    <div key="1" className="col-lg mt-3" style={{marginBottom: "10px"}}>
                        <Ripples>
                            <Button className="btn btn-success" onClick={() => {
                                window.location.href = "/addPassenger";
                            }}>Add Passenger <i className="fa fa-user-plus"/></Button>
                        </Ripples>
                    </div>
                    <div key="2">
                        <PassengerList/>
                    </div>
                </QueueAnim>
            </div>
        )
    };
}