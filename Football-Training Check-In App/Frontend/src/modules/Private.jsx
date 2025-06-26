import { useEffect, useState } from "react"
import axios from "axios"
import AuthService from "../services/auth.service"

export default function Private() {
    const [item, setItem] = useState('')

    useEffect(() => {
        axios.get("http://localhost:8080/public", AuthService.getJwtHeader())  
        .then((response) => {
            setItem(response.data)
        })
    }, [])

    return (
        <div>
            <h1>{item}</h1>
        </div>
    )
}