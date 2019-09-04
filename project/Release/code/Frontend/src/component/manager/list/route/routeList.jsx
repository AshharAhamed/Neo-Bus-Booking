import React, {Component} from 'react'
import OneRoute from "./oneRoute"
import ManagerService from '../../../../services/ManagerService'
import QueueAnim from "rc-queue-anim";

export default class RouteList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            routes: []
        };
        this.managerService = new ManagerService();
    }

    componentDidMount() {
        this.managerService.getAllRoutes().then(response => {
            this.setState({routes: response.data});
        }).catch(function (error) {
            console.log(error);
        })
    }

    tabRow() {
        return this.state.routes.map(function (object, i) {
            return <OneRoute obj={object} key={i}/>;
        });
    }

    render() {
        return (
            <div>
                <table className="table  table-bordered table-hover table-striped ">
                    <thead>
                    <tr>
                        <th className="text-center"><QueueAnim><div key="1">Route</div></QueueAnim></th>
                        <th className="text-center"><QueueAnim><div key="1">Route No</div></QueueAnim></th>
                        <th className="text-center"><QueueAnim><div key="1">Bus Halts</div></QueueAnim></th>
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