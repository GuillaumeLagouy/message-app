export async function postMessage(data: { content: string }) {
  const response = await fetch('http://localhost:8080/message', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  });

  if (!response.ok) {
    throw new Error('Failed to send message');
  }

  const responseBody = await response.text();
  return responseBody ? JSON.parse(responseBody) : null;
}

export async function getMessages() {
  const response = await fetch('http://localhost:8080/messages');

  if (!response.ok) {
    throw new Error('Failed to retrieve messages');
  }

  return response;
}
