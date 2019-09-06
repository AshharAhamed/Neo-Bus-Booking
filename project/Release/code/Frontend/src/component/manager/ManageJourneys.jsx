import React, {Component} from 'react';
import JourneyList from "./list/journey/journeyList";
import QueueAnim from "rc-queue-anim";

export default class ManageJourney extends Component {
    render() {
        return (
            <div>
                <h4 style={{marginTop: "10px"}}>On Going Journeys </h4>
                <QueueAnim duration="3000" interval="400">
                    <div key="2">
                        <JourneyList/>
                    </div>
                </QueueAnim>
            </div>
        )
    };
}