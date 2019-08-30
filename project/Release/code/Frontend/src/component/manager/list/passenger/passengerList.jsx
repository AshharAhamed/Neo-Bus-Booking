import React, {Component} from 'react'
import OnePassenger from "./onePassenger"
import TicketService from '../../../../services/ManagerService'
import QueueAnim from "rc-queue-anim";

export default class PassengerList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            passengers: []
        };
        this.ticketService = new TicketService();
    }

    componentDidMount() {
        this.ticketService.getAllPassengers().then(response => {
            this.setState({passengers: response.data});
        }).catch(function (error) {
            console.log(error);
        })
    }

    tabRow() {
        return this.state.passengers.map(function (object, i) {
            return <OnePassenger obj={object} key={i}/>;
        });
    }

    render() {
        return (
            <div>
                <h4>Registered Passengers </h4>
                <table className="table  table-bordered table-hover table-striped ">
                    <thead>
                    <tr>
                        <th><QueueAnim><div key="1">Username</div></QueueAnim></th>
                        <th><QueueAnim><div key="1">First Name</div></QueueAnim></th>
                        <th><QueueAnim><div key="1">Last Name</div></QueueAnim></th>
                        <th><QueueAnim><div key="1">Email</div></QueueAnim></th>
                        <th><QueueAnim><div key="1">Phone</div></QueueAnim></th>
                        <th><QueueAnim><div key="1">Action</div></QueueAnim></th>
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