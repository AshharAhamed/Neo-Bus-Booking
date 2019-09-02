import React, {Component} from 'react';
import AdminBody from '../manager/Body'

export default class InspectorDashboard extends Component {
    render() {
        return (
            <div>
                <div>Logged in as Inspector</div>
                <AdminBody/>
            </div>
        )
    };
}