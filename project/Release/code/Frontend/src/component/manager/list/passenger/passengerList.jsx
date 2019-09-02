import React, {Component} from 'react'
import OnePassenger from "./onePassenger"
import ManagerService from '../../../../services/ManagerService'
import QueueAnim from "rc-queue-anim";

export default class PassengerList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            passengers: []
        };
        this.managerService = new ManagerService();
    }

    componentDidMount() {
        this.managerService.getAllPassengers().then(response => {
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
                <table className="table  table-bordered table-hover table-striped ">
                    <thead>
                    <tr>
                        <th className="text-center"><QueueAnim><div key="1">Card No</div></QueueAnim></th>
                        <th className="text-center"><QueueAnim><div key="1">Name</div></QueueAnim></th>
                        <th className="text-center"><QueueAnim><div key="1">Email</div></QueueAnim></th>
                        <th className="text-center"><QueueAnim><div key="1">Phone</div></QueueAnim></th>
                        <th className="text-center"><QueueAnim><div key="1">Type</div></QueueAnim></th>
                        <th className="text-center"><QueueAnim><div key="1">NIC/Passport ID</div></QueueAnim></th>
                        <th className="text-center"><QueueAnim><div key="1">Credit Balance</div></QueueAnim></th>
                        <th className="text-center"><QueueAnim><div key="1">Loan Amount</div></QueueAnim></th>
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