import { useEffect, useState } from 'react';
import { getMessages } from '../services/apiService';

type Message = {
  id: number;
  message: string;
};

export default function MessageList() {
  const [messages, setMessages] = useState<Message[]>([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    getMessages()
      .then((response) => {
        console.log('Response Status:', response.status);
        if (!response.ok) {
          throw new Error('Erreur de réseau');
        }
        return response.json();
      })
      .then((data) => setMessages(data))
      .catch((err) => setError(err.message));
  }, []);

  if (error) {
    return <p className="border-2 border-red-500">Erreur : {error}</p>;
  }
  return (
    <div>
      <h1>Liste des messages</h1>
      <ul>
        {messages.map((message) => (
          <li key={message.id}>{message.message}</li>
        ))}
      </ul>
    </div>
  );
}
