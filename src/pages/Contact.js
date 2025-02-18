import React from "react";
import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min";
import "../App.css";
import "../styles/Button.css";
import logo from "../assets/logo.png"; // Importing the logo
import avatar from "../assets/avatar.png"; // Importing the avatar

const Contact = () => {
  const [loggedInUser, setLoggedInUser] = useState(null);
  const navigate = useNavigate(); // Hook for navigation

  useEffect(() => {
    const user = localStorage.getItem("loggedInUser");
    if (user) {
      setLoggedInUser(user);
    }
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("loggedInUser");
    setLoggedInUser(null);
    navigate("/"); // Redirect to Home after logout
  };
  return (
    <>
        {/* <--- NAVBAR --->*/}
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
            <li className="nav-item">
              <Link className="nav-link" to="/">Home</Link>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#events">Events</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#blogs">Blogs</a>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/shop">Shop</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/contact">Contact</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/orders">Orders</Link>
            </li>

            {/* Conditionally render Login or Profile Image */}
            {!loggedInUser ? (
              <li className="nav-item">
                <Link className="nav-link" to="/login">Login</Link>
              </li>
            ) : (
              <li className="nav-item dropdown">
                <img 
                  src={avatar} 
                  className="rounded-circle mt-2" 
                  alt="Profile" 
                  width="30" 
                  height="30" 
                  data-bs-toggle="dropdown" 
                  aria-expanded="false" 
                  style={{ cursor: "pointer" }}
                />
                <ul className="dropdown-menu dropdown-menu-end mt-3 bg-dark">
                  <li>
                    <button id="logoutbtn" className="dropdown-item text-light" onClick={handleLogout}>
                      Logout
                    </button>
                  </li>
                </ul>
              </li>
            )}
          </ul>
        </div>
      </div>
    </nav>

        <div className="container-fluid">
            <div className="row align-items-center">
                <div className="col-md-12">
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3671.3639091117775!2d72.53191767554426!3d23.047116379156645!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x395e84a52d2df2e3%3A0x5d4f5ada7d59b99f!2sSRAG%20Tennis%20Academy!5e0!3m2!1sen!2sin!4v1734326232435!5m2!1sen!2sin" width="100%" height="630" style={{ border: "0" }} allowFullScreen loading="lazy" referrerPolicy="no-referrer-when-downgrade"></iframe>
                </div>
            </div>
        </div>

    {/* <--- SECTION-1 --->*/}
    <div className="container-fluid my-5">
      <div className="row text-center">
        {/* Left Section: Contact Information */}
        <div className="col-md-6">
          <div className="p-3">
            <h4
              className="mx-auto"
              style={{
                fontSize: 20,
                fontFamily:
                  "'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif",
                letterSpacing: "5px",
                marginTop: "100px",
              }}
            >
              CONTACT US
            </h4>
            <h1 className="mx-auto my-5" style={{ fontFamily: "Oswald, sans-serif" }}>
              HAVE QUESTIONS ?
            </h1>
            <h1 className="mx-auto mb-5" style={{ fontFamily: "Oswald, sans-serif", marginTop: "-40px" }}>
              GET IN TOUCH!
            </h1>
            <p className="mx-auto text-muted">
              Adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim.
            </p>
            <p className="text-muted">
              11792 London Rd, Derby, OH 43117
            </p>
            <p className="text-muted" style={{ fontFamily: "Oswald, sans-serif", fontWeight: 500 }}>+1 800 555 45 65</p>
            <p className="text-muted">info@email.com</p>
          </div>
        </div>

        {/* Right Section: Contact Form */}
        <div className="col-md-6">
          <div>
            <form action="/action_page.php" style={{ marginTop: "200px" }}>
              {/* Name Input */}
              <div className="mb-3">
                <input type="text" name="name" className="form-control mx-auto" placeholder="Name" style={{ backgroundColor: "transparent", borderBottom: "1px solid #ddd", padding: "10px" }} required />
              </div>

              {/* Email Input */}
              <div className="mb-3">
                <input type="email" name="email" className="form-control mx-auto" placeholder="Email" style={{ backgroundColor: "transparent", borderBottom: "1px solid #ddd", padding: "10px" }} required/>
              </div>

              {/* Phone Input */}
              <div className="mb-3">
                <input type="tel" name="phone" className="form-control mx-auto" placeholder="Phone (e.g. +1 800-555-4565)" pattern="^\+?[0-9\s-]{7,15}$" style={{ backgroundColor: "transparent", borderBottom: "1px solid #ddd", padding: "10px" }} required/>
              </div>

              {/* Subject Input */}
              <div className="mb-3">
                <input type="text" name="subject" className="form-control mx-auto" placeholder="Subject" style={{ backgroundColor: "transparent", borderBottom: "1px solid #ddd", padding: "10px" }} required/>
              </div>

              {/* Message Input */}
              <div className="mb-3">
                <textarea name="message" className="form-control mx-auto" placeholder="Your Message" rows="4" style={{ backgroundColor: "transparent", borderBottom: "1px solid #ddd", padding: "10px" }} required ></textarea>
              </div>

              {/* Submit Button */}
              <button type="submit" className="btn text-light" style={{ marginTop: "30px", backgroundColor: "#2eac6d", height: "50px", width: "200px", borderRadius: "30px", boxShadow: "0px 4px 6px rgba(0, 0, 0, 0.2)", transition: "all 0.3s" }} >
                GET IN TOUCH
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>

    {/* FOOTER */}
    <footer className="bg-dark text-light text-center py-3">
        <div>© Raj Patel · <a href="#" className="text-decoration-none text-light">Privacy</a> · <a href="#" className="text-decoration-none text-light">Terms</a></div>
        <a href="#" className="text-decoration-none text-light">Back to top</a>
    </footer>
    </>
  );
};

export default Contact;
