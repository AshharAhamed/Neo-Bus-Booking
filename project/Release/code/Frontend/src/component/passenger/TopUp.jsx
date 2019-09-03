import React, {Component} from 'react';
import PassengerService from '../../services/PassengerService'
import UserService from "../../services/UserService";
import Select from 'react-select';
import QueueAnim from "rc-queue-anim";

export default class PassengerTopUp extends Component {
    constructor(props) {
        super(props);
        this.state = {
            amount: '',
            cardList: [],
            cardOptions : [],
            selectedCard : null
        };
        this.passengerService = new PassengerService();
        this.userService = new UserService();
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
        this.clearForm = this.clearForm.bind(this);
        this.loadPassengerAccount(this.userService.username);
    }

    handleChange = selectedCard => {
        this.setState({ selectedCard });
    };

    onChange(e) {
        this.setState({
            [e.target.name]: e.target.value,
        });
    }

    clearForm(e) {
        this.setState({
            amount: ''
        });
    }

    loadPassengerAccount(cardNo) {
        this.passengerService.getCards(this.userService.username).then( response => {
            this.setState( { cardList : response.data});
            this.setState({cardOptions : this.tabRow()});
        }).catch(error => {
            console.log(error)
        })
    }

    tabRow() {
        if(this.state.cardList !== null && this.state.cardList !== ''){
            return this.state.cardList.map(function (x, y) {
                const myVal = { value : '' + x  + '' , label: + '' + x + ''};
                return myVal;
            });
        }
    }

    onSubmit(e) {
        e.preventDefault();
        this.passengerService.topUp(this.userService.username, {
            "paymentCardNo" : this.state.selectedCard.value,
            "amount" : this.state.amount
        }).then( response => {
            alert(response.data)
        }).catch( error => {
            console.log(error)
        })
    }


    render() {
        const { selectedCard } = this.state;

        return (
            <div>

                <div className="container p-2" style={{paddingBottom: '500px'}}>
                    <form onSubmit={this.onSubmit}>
                        <QueueAnim duration="1000" interval="400">

                            <div key="1" className="wrap-input100 validate-input float-sm-left" data-validate="Name is required">
                                <span className="label-input100">Amount : </span>
                                <input className="input100" type="number" required={true} value={this.state.amount}
                                       onChange={this.onChange} name="amount" min="0" placeholder="Rs. 100"/>
                                <span className="focus-input100"/>
                            </div>

                            <div className="input100-select" style={{marginLeft : "800px", marginTop : "100px"}}>
                                <span className="label-input100">Select Card</span><br/>
                                <div>
                                    <Select onChange={this.handleChange}  value={selectedCard} options={this.state.cardOptions} />
                                </div>
                            </div>
                            <br/><br/><br/><br/>

                            <div className="container-contact100-form-btn float-left">
                                <div className="wrap-contact100-form-btn">
                                    <div className="contact100-form-bgbtn"/>
                                    <button className="contact100-form-btn">
							<span>
                                Top Up  <i className="fa fa-paper-plane"/>
                            <input type="submit" value=""/>
							</span>
                                    </button>
                                </div>
                            </div>

                            <div style={{marginTop: "20px", marginLeft: "200px"}}>
                                <button style={{width: "100px"}} className="btn btn-secondary btn-block"
                                        onClick={this.clearForm}>Clear <i
                                    className="fa fa-eraser"/>
                                </button>
                            </div>
                        </QueueAnim>
                    </form>
                </div>
            </div>
        );
    }
}