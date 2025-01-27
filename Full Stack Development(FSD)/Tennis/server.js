const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const cors = require('cors');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
const User = require('./models/User');

const app = express();
app.use(bodyParser.json());
app.use(cors());

const PORT = process.env.PORT || 5000;
const JWT_SECRET = 'your_secret_key_here';  // Use a secure key for JWT

// MongoDB connection
mongoose.connect('mongodb://localhost:27017/userAuth', { useNewUrlParser: true, useUnifiedTopology: true })
  .then(() => console.log('MongoDB connected'))
  .catch(err => console.log('MongoDB connection error: ', err));

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});

app.post('/api/signup', async (req, res) => {
  const { username, password } = req.body;

  try {
      // Check if user already exists
      const existingUser = await User.findOne({ username });
      if (existingUser) {
          return res.status(400).json({ message: 'Username already exists' });
      }

      // Hash the password
      const hashedPassword = await bcrypt.hash(password, 10);

      // Create new user and store in MongoDB
      const newUser = new User({ username, password: hashedPassword });
      await newUser.save();

      res.status(201).json({ message: 'Signup successful' });
  } catch (err) {
      res.status(500).json({ message: 'Error signing up user' });
  }
});

app.post('/api/login', async (req, res) => {
  const { username, password } = req.body;

  try {
      // Find user by username
      const user = await User.findOne({ username });
      if (!user) {
          return res.status(400).json({ message: 'Invalid username or password' });
      }

      // Compare hashed password
      const passwordMatch = await bcrypt.compare(password, user.password);
      if (!passwordMatch) {
          return res.status(400).json({ message: 'Invalid username or password' });
      }

      // Generate JWT token
      const token = jwt.sign({ userId: user._id }, JWT_SECRET, { expiresIn: '1h' });

      res.json({ message: 'Login successful', token });
  } catch (err) {
      res.status(500).json({ message: 'Error logging in user' });
  }
});

function authenticate(req, res, next) {
  const token = req.headers['authorization'];
  if (!token) {
      return res.status(403).json({ message: 'No token provided' });
  }

  jwt.verify(token, JWT_SECRET, (err, decoded) => {
      if (err) {
          return res.status(500).json({ message: 'Failed to authenticate token' });
      }
      req.userId = decoded.userId;
      next();
  });
}

app.get('/api/protected', authenticate, (req, res) => {
  res.json({ message: 'You have access to this route' });
});