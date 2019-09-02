import React, {Component} from 'react'
import OneManager from "./oneAdmin"
import TicketService from '../../../../services/ManagerService'
import QueueAnim from "rc-queue-anim";

export default class AdminList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            managers: []
        };
        this.ticketService = new TicketService();
    }

    componentDidMount() {
        this.ticketService.getAllManagers().then(response => {
            this.setState({managers: response.data});
        }).catch(function (error) {
            console.log(error);
        })
    }

    tabRow() {
        return this.state.managers.map(function (object, i) {
            return <OneManager obj={object} key={i}/>;
        });
    }

    render() {
        return (
            <div>
                <table className="table  table-bordered table-hover table-striped ">
                    <thead>
                    <tr>
                        <th className="text-center"><QueueAnim><div key="1">Username</div></QueueAnim></th>
                        <th className="text-center"><QueueAnim><div key="1">First Name</div></QueueAnim></th>
                        <th className="text-center"><QueueAnim><div key="1">Last Name</div></QueueAnim></th>
                        <th className="text-center"><QueueAnim><div key="1">Email</div></QueueAnim></th>
                        <th className="text-center"><QueueAnim><div key="1">Phone</div></QueueAnim></th>
                        <th className="text-center"><QueueAnim><div key="1">Action</div></QueueAnim></th>
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