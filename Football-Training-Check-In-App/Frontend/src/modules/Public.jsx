import { useEffect, useState } from "react"
import axios from "axios"

export default function Public() {
    const [liste, setListe] = useState([])
    const [item, setItem] = useState('')

    const buttonClicked = (event) => {
        console.log("button clicked" + event.target.innerText)
    }

    useEffect(() => {
        axios.get("http://localhost:8080/public")  
        .then((response) => {
            setListe(response.data)
        })
    }, [])

    return (
        <div>
            <h1>Public</h1>
            <p>Hurry, es funktioniert</p>
            <hr />
            <button onClick={buttonClicked}> Button 1 </button>
            <button onClick={buttonClicked}> Button 2 </button>
            <button onClick={buttonClicked}> Button 3 </button>

            <p>Neuer Item: </p>
            <input type="text" 
            onChange={(event) => setItem(event.target.value)} 
            />
            <button onClick={(event) => {
                setListe([...liste, item])
                setItem('')
            }}>
                Hinzufügen
            </button>


            <ul>
                {liste.map((item) => {
                    return <li key={item}>{item}</li>
                })}
            </ul>
        </div>
    )
}