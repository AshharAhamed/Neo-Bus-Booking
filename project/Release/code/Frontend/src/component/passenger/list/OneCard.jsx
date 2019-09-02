import React, {Component} from 'react'

class OneCard extends Component {
    constructor(props) {
        super(props);
        this.delete = this.delete.bind(this);
    }

    delete() {
        // this.ticketService.deleteUser(this.props.obj.username).then(response => {
        //     alert(response.data);
        //     window.location.reload();
        // }).catch(err => {
        //     console.log(err)
        // })
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
