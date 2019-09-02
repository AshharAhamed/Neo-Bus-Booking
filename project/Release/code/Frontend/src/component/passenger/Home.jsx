import React, {Component} from 'react';
import AdminBody from '../manager/Body'

export default class PassengerDashboard extends Component {
    render() {
        return (
            <div>
                <div>Logged in as Passenger</div>
                <AdminBody/>
            </div>
        )
    };
}