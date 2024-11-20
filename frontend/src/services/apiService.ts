import { Message } from '../types/MessageType';

export async function postMessage(content: string): Promise<string> {
  const response = await fetch('http://localhost:8080/message', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(content),
  });
  if (!response.ok) {
    throw new Error('Failed to send message');
  }

  return await response.text();
}

export async function updateMessage(data: {
  id: number;
  content: string;
}): Promise<Message> {
  const response = await fetch(`http://localhost:8080/message/${data.id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  });
  if (!response.ok) {
    throw new Error('Failed to update message');
  }

  const responseBody = await response.text();
  return responseBody ? JSON.parse(responseBody) : null;
}

export async function getMessages(): Promise<Message[]> {
  const response = await fetch('http://localhost:8080/messages');

  if (!response.ok) {
    throw new Error('Failed to retrieve messages');
  }

  return response.json();
}
