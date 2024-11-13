import { useEffect, useState } from 'react';
import './App.css';

function App() {
  type Message = {
    id: number;
    message: string;
  };

  const [messages, setMessages] = useState<Message[]>([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch('http://localhost:8080/messages')
      .then((response) => {
        if (!response.ok) {
          throw new Error('Erreur de réseau');
        }
        return response.json();
      })
      .then((data) => setMessages(data))
      .catch((err) => setError(err.message));
  }, []); // [] pour que l'effet ne s'exécute qu'une seule fois

  if (error) {
    return <p className="border-2 border-red-500">Erreur : {error}</p>;
  }

  return (
    <div>
      <h1 className="border-2 border-red-500">Liste des messages</h1>
      <ul>
        {messages.map((message) => (
          <li key={message.id}>{message.message}</li>
        ))}
      </ul>
    </div>
  );
}

export default App;
