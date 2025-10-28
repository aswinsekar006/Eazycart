import React, { useState } from "react";

function App() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");

  // Signup
  const handleSignup = async () => {
    const response = await fetch("http://localhost:8080/api/users/signup", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ name, email, password }),
    });
    const data = await response.text();
    setMessage(data);
  };

  // Login
  const handleLogin = async () => {
    const response = await fetch("http://localhost:8080/api/users/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, password }),
    });
    const data = await response.text();
    setMessage(data);
  };

  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h1>EazyCart</h1>
      <input
        type="text"
        placeholder="Name (for signup)"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />
      <br /><br />
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <br /><br />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <br /><br />

      <button onClick={handleSignup}>Signup</button>
      <button onClick={handleLogin} style={{ marginLeft: "10px" }}>Login</button>

      <h3 style={{ marginTop: "20px", color: "green" }}>{message}</h3>
    </div>
  );
}

export default App;
