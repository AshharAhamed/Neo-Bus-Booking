import React, {Component} from 'react';
import {Button} from "react-bootstrap";
import Ripples from "react-ripples";
import QueueAnim from "rc-queue-anim";
import RouteList from "./list/route/routeList";

export default class ManageRoute extends Component {
    render() {
        return (
            <div>
                <h4 style={{marginTop: "10px"}}>Routes </h4>
                <QueueAnim duration="3000" interval="400">
                    <div key="1" className="col-lg mt-3" style={{marginBottom: "10px"}}>
                        <Ripples>
                            <Button className="btn btn-success" onClick={() => {
                                window.location.href = "/addRoute";
                            }}>Add Route <i className="fa fa-user-plus"/></Button>
                        </Ripples>
                    </div>
                    <div key="2">
                        <RouteList/>
                    </div>
                </QueueAnim>
            </div>
        )
    };
}