import React, {Component} from 'react'
import TicketService from '../../../../services/ManagerService'

class OnePassenger extends Component {
    constructor(props) {
        super(props);
        this.ticketService = new TicketService();
        this.delete = this.delete.bind(this);
    }

    delete() {
        this.ticketService.deleteUser(this.props.obj.username).then(response => {
            alert(response.data);
            window.location.reload();
        }).catch(err => {
            console.log(err)
        })
    }

    render() {
        return (
            <tr>
                <td>{this.props.obj.username}</td>
                <td>{this.props.obj.firstName}</td>
                <td>{this.props.obj.lastName}</td>
                <td>{this.props.obj.email}</td>
                <td>{this.props.obj.contact}</td>
                <td>
                    <button onClick={this.delete} className="btn btn-danger">Delete  <i className="fa fa-trash"/></button>
                </td>
            </tr>
        )
    }
}

export default OnePassenger;
