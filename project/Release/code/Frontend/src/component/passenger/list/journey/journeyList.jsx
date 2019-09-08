import React, {Component} from 'react'
import OneJourney from "./oneJourney"
import ManagerService from '../../../../services/ManagerService'
import QueueAnim from "rc-queue-anim";

export default class JourneyListPassenger extends Component {
    constructor(props) {
        super(props);
        this.state = {
            journeys: []
        };
        this.managerService = new ManagerService();
    }

    componentDidMount() {
        this.managerService.getAllActiveJourneys().then(response => {
            this.setState({journeys: response.data});
        }).catch(function (error) {
            console.log(error);
        })
    }

    tabRow() {
        return this.state.journeys.map(function (object, i) {
            return <OneJourney obj={object} key={i}/>;
        });
    }

    render() {
        return (
            <div>
                <table className="table  table-bordered table-hover table-striped ">
                    <thead>
                    <tr>
                        <th className="text-center"><QueueAnim><div key="1">Route Name</div></QueueAnim></th>
                        <th className="text-center"><QueueAnim><div key="1">Bus No</div></QueueAnim></th>
                        <th className="text-center"><QueueAnim><div key="1">Next Station</div></QueueAnim></th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.tabRow()}
                    </tbody>
                </table>
            </div>
        );
    }
}