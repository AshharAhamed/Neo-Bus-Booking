import React, {Component} from 'react'

export default class OneJourneyPassenger extends Component {
    render() {
        return (
            <tr>
                <td>{this.props.obj.routeName}</td>
                <td>{this.props.obj.busNo}</td>
                <td>{this.props.obj.nextStation}</td>
            </tr>
        )
    }
}