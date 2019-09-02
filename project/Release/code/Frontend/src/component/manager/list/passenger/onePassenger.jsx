import React, {Component} from 'react'
import ManagerService from '../../../../services/ManagerService'

class OnePassenger extends Component {
    constructor(props) {
        super(props);
        this.managerService = new ManagerService();
        this.delete = this.delete.bind(this);
        this.setPassport(props)
    }

    setPassport(props) {
        if (this.props.obj.nic == null)
            this.props.obj.nic = this.props.obj.passport;
    }

    delete() {
        this.managerService.deletePassenger(this.props.obj.cardNo).then(response => {
            alert(response.data);
            window.location.reload();
        }).catch(err => {
            console.log(err)
        })
    }

    render() {
        return (
            <tr>
                <td>{this.props.obj.cardNo}</td>
                <td>{this.props.obj.firstName} {this.props.obj.lastName}</td>
                <td>{this.props.obj.email}</td>
                <td>{this.props.obj.contact}</td>
                <td>{this.props.obj.type}</td>
                <td>{this.props.obj.nic}</td>
                <td>{this.props.obj.creditBalance}</td>
                <td>{this.props.obj.loanAmount}</td>
                <td>
                    <button onClick={this.delete} className="btn btn-danger">Delete  <i className="fa fa-trash"/></button>
                </td>
            </tr>
        )
    }
}

export default OnePassenger;