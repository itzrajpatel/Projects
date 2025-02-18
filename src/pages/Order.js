import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min";
import "../App.css";
import "../styles/Button.css";
import logo from "../assets/logo.png";
import avatar from "../assets/avatar.png";
import photo from "../assets/photo-8.webp";

const Order = () => {
    const [loggedInUser, setLoggedInUser] = useState(null);
    const [orders, setOrders] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const user = localStorage.getItem("loggedInUser");
        if (user) {
            setLoggedInUser(user);
            fetchOrders(user);
        }
    }, []);

    // Fetch orders for the logged-in user
    const fetchOrders = (user) => {
        const storedOrders = JSON.parse(localStorage.getItem(`cart_${user}`)) || [];
        setOrders(storedOrders);
    };

    // Handle quantity increment & decrement
    const handleQuantityChange = (index, delta) => {
        const updatedOrders = [...orders];
        const newQuantity = updatedOrders[index].quantity + delta;

        if (newQuantity >= 1) {
            updatedOrders[index].quantity = newQuantity;
            setOrders(updatedOrders);
            localStorage.setItem(`cart_${loggedInUser}`, JSON.stringify(updatedOrders));
        }
    };

    // Remove an order (Cancel Order)
    const handleCancelOrder = (index) => {
        const updatedOrders = orders.filter((_, i) => i !== index);
        setOrders(updatedOrders);
        localStorage.setItem(`cart_${loggedInUser}`, JSON.stringify(updatedOrders));
    };

    // Handle Payment (Redirect or API Call)
    const handleMakePayment = (order) => {
        alert(`Proceeding to payment for ${order.name}.`);
        // You can integrate a payment gateway API here.
    };

    // Logout Function
    const handleLogout = () => {
        localStorage.removeItem("loggedInUser");
        setLoggedInUser(null);
        navigate("/");
    };

    return (
        <div style={{ backgroundImage: `url(${photo})`, backgroundRepeat: "no-repeat", backgroundPosition: "center center", backgroundAttachment: "fixed", backgroundSize: "cover", minHeight: "100vh" }}>
            {/* NAVBAR */}
            <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                <div className="container-fluid" style={{ gap: "15px" }}>
                    <img alt="Logo" src={logo} height="50px" width="50px" style={{ margin: "0 20px" }} />
                    <a className="navbar-brand" href="/" style={{ fontFamily: "Ovo, serif", fontSize: "19px", fontWeight: "bold" }}>
                        Indian Tennis Academy
                    </a>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarNav">
                        <ul className="navbar-nav ms-auto" style={{ gap: "12px" }}>
                            <li className="nav-item"><Link className="nav-link" to="/">Home</Link></li>
                            <li className="nav-item"><a className="nav-link" href="#events">Events</a></li>
                            <li className="nav-item"><a className="nav-link" href="#blogs">Blogs</a></li>
                            <li className="nav-item"><Link className="nav-link" to="/shop">Shop</Link></li>
                            <li className="nav-item"><Link className="nav-link" to="/contact">Contact</Link></li>
                            <li className="nav-item"><Link className="nav-link" to="/orders">Orders</Link></li>

                            {!loggedInUser ? (
                                <li className="nav-item"><Link className="nav-link" to="/login">Login</Link></li>
                            ) : (
                                <li className="nav-item dropdown">
                                    <img src={avatar} className="rounded-circle mt-2" alt="Profile" width="30" height="30"
                                        data-bs-toggle="dropdown" aria-expanded="false" style={{ cursor: "pointer" }} />
                                    <ul className="dropdown-menu dropdown-menu-end mt-3 bg-dark">
                                        <li><button id="logoutbtn" className="dropdown-item text-light" onClick={handleLogout}>Logout</button></li>
                                    </ul>
                                </li>
                            )}
                        </ul>
                    </div>
                </div>
            </nav>

            {/* Orders Section */}
            <div className="container-fluid">
                <h1 className="text-center my-5 text-light" style={{ fontFamily: "Cormorant Garamond, serif", fontWeight: "bold", fontSize: "60px" }}>
                    ORDERS
                </h1>
                <h1 className="text-center mt-5 text-light">
                    <i style={{ fontSize: "35px" }} className='fas'>&#xf107;</i>
                </h1>

                {/* Display Orders */}
                <div className="row justify-content-center my-5">
                    {orders.length === 0 ? (
                        <h4 className="text-center text-danger">No orders found.</h4>
                    ) : (
                        orders.map((order, index) => (
                            <div key={index} className="col-md-4 mb-4">
                                <div className="card p-3 shadow" style={{ background: "rgba(255, 255, 255, 0.2)", borderRadius: "16px", boxShadow: "0 4px 30px rgba(0, 0, 0, 0.1)", backdropFilter: "blur(5px)", border: "1px solid rgba(255, 255, 255, 0.3)"}}>
                                    <img src={order.image} alt={order.name} className="card-img-top"
                                        style={{ height: "200px", objectFit: "contain" }} />
                                    <div className="card-body">
                                        <h5 className="card-title mb-4 text-center text-light">{order.name}</h5>

                                        {/* Quantity Incrementer */}
                                        <div className="d-flex justify-content-center align-items-center mb-3">
                                            <button 
                                                className="btn btn-danger" 
                                                onClick={() => handleQuantityChange(index, -1)}
                                                disabled={order.quantity === 1} 
                                            >
                                                -
                                            </button>
                                            <span className="mx-3 fs-5 text-light">{order.quantity}</span>
                                            <button 
                                                className="btn btn-success" 
                                                onClick={() => handleQuantityChange(index, 1)}
                                            >
                                                +
                                            </button>
                                        </div>

                                        {/* Dynamic Price Calculation */}
                                        <p className="card-text text-light">
                                            Price: ${order.price * order.quantity}
                                        </p>

                                        {/* Buttons (Make Payment & Cancel Order) */}
                                        <div className="d-flex justify-content-between">
                                            <button 
                                                className="btn btn-primary w-50 me-2"
                                                onClick={() => handleMakePayment(order)}
                                            >
                                                Make Payment
                                            </button>
                                            <button 
                                                className="btn btn-outline-danger w-50"
                                                onClick={() => handleCancelOrder(index)}
                                            >
                                                Cancel Order
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        ))
                    )}
                </div>
            </div>

            {/* <--- FOOTER ---> */}
            {orders.length > 0 && (
                <footer className="bg-dark text-light text-center py-3">
                    <div>© Raj Patel · <a href="#" className="text-decoration-none text-light">Privacy</a> · <a href="#" className="text-decoration-none text-light">Terms</a></div>
                    <a href="#" className="text-decoration-none text-light">Back to top</a>
                </footer>
            )}
        </div>
    );
};

export default Order;