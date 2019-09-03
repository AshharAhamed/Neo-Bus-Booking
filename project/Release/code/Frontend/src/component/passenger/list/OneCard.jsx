import React, {Component} from 'react'
import PassengerService from "../../../services/PassengerService";
import UserService from "../../../services/UserService";

class OneCard extends Component {
    constructor(props) {
        super(props);
        this.delete = this.delete.bind(this);
        this.passengerService = new PassengerService();
        this.userService = new UserService();
    }

    delete() {
        this.passengerService.deleteCard(this.userService.username, this.props.obj.cardNo).then(response => {
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
                <td>
                    <button onClick={this.delete} className="btn btn-danger">Delete  <i className="fa fa-trash"/></button>
                </td>
            </tr>
        )
    }
}

export default OneCard;
