import AuthService from "../services/auth.service";
import { Link } from "react-router-dom";      // <-- Import Link
import "../styles/Navigation.css";

export default function Navigation() {
  return (
    <nav>
      <ul>
        <li>
          <Link to="/">Home</Link>            {/* <-- Link statt a */}
        </li>
        <li>
          <Link to="/about">About</Link>     {/* <-- Link statt a */}
        </li>
        <li>
          <Link to="/public">Public</Link>    {/* <-- Link statt a */}
        </li>
        {AuthService.getCurrentUser() ? (
          <>
            <li>
              <Link to="/dashboard">Dashboard</Link> {/* <-- Link statt a */}
            </li>
            <li>
              <Link to="/logout">Logout</Link>   {/* <-- Link statt a */}
            </li>
          </>
        ) : (
          <li>
            <Link to="/login">Login</Link>        {/* <-- Link statt a */}
          </li>
        )}
      </ul>
    </nav>
  );
}
