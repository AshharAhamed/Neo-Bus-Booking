import React, {Component} from 'react'
import ManagerService from '../../../../services/ManagerService'

class OnePassenger extends Component {
    constructor(props) {
        super(props);
        this.managerService = new ManagerService();
        this.delete = this.delete.bind(this);
    }

    delete() {
        this.managerService.deleteJourney(this.props.obj.journeyID).then(response => {
            alert(response.data);
            window.location.reload();
        }).catch(err => {
            console.log(err)
        })
    }

    render() {
        return (
            <tr>
                <td>{this.props.obj.routeName}</td>
                <td>{this.props.obj.busNo}</td>
                <td>{this.props.obj.nextStation}</td>
                <td>{this.props.obj.startTime}</td>
                <td>
                    <button onClick={this.delete} className="btn btn-danger">Abort  <i className="fa fa-times"/></button>
                </td>
            </tr>
        )
    }
}

export default OnePassenger;