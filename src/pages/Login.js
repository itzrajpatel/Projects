import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min";
import "../App.css";
import "../styles/Login.css";
import logo from "../assets/logo.png"; // Importing the logo

const LoginSignup = () => {
    const [isSignup, setIsSignup] = useState(false);
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    // Check if the user is already logged in
    useEffect(() => {
        if (localStorage.getItem("loggedInUser")) {
            alert("You are already logged in!");
            navigate("/home");
        }
    }, [navigate]);

    // Handle Signup
    const handleSignup = (event) => {
        event.preventDefault();
        
        if (localStorage.getItem(username)) {
            alert("Username already exists! Please choose a different username.");
            return;
        }

        localStorage.setItem(username, JSON.stringify({ password }));
        alert("Signup successful! Please login.");
        setIsSignup(false);
        setUsername("");
        setPassword("");
    };

    // Handle Login
    const handleLogin = (event) => {
        event.preventDefault();
        
        const storedUser = localStorage.getItem(username);
        if (!storedUser) {
            alert("User not found. Please sign up first.");
            return;
        }

        const userData = JSON.parse(storedUser);
        if (userData.password !== password) {
            alert("Incorrect password. Please try again.");
            return;
        }

        localStorage.setItem("loggedInUser", username);
        alert("Login successful!");
        navigate("/"); // Redirect to home after login
    };

    // Handle Logout
    const handleLogout = () => {
        localStorage.removeItem("loggedInUser");
        alert("Logged out successfully!");
        navigate("/login");
    };

    return (
        <div className="login-container">
            <a href="/">
                <img src={logo} className="brand-logo" alt="Your Brand Logo" />
            </a>
            <div className="main">
                {isSignup ? (
                    <div className="signup">
                        <form onSubmit={handleSignup}>
                            <label>Sign up</label>
                            <input 
                                type="text" 
                                value={username} 
                                onChange={(e) => setUsername(e.target.value)} 
                                placeholder="Username" 
                                required 
                            />
                            <input 
                                type="password" 
                                value={password} 
                                onChange={(e) => setPassword(e.target.value)} 
                                placeholder="Password" 
                                required 
                            />
                            <button id="loginsignup" type="submit">Sign up</button>
                        </form>
                        <p className="my-5">Already have an account? <span onClick={() => setIsSignup(false)}>Login</span></p>
                    </div>
                ) : (
                    <div className="login">
                        <form onSubmit={handleLogin}>
                            <label>Login</label>
                            <input 
                                type="text" 
                                value={username} 
                                onChange={(e) => setUsername(e.target.value)} 
                                placeholder="Username" 
                                required 
                            />
                            <input 
                                type="password" 
                                value={password} 
                                onChange={(e) => setPassword(e.target.value)} 
                                placeholder="Password" 
                                required 
                            />
                            <button id="loginsignup" type="submit">Login</button>
                        </form>
                        <p className="my-5">Don't have an account? <span onClick={() => setIsSignup(true)}>Sign up</span></p>
                    </div>
                )}
            </div>
        </div>
    );
};

export default LoginSignup;
