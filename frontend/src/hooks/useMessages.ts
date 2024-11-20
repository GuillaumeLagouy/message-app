import { useCallback, useEffect, useState } from 'react';
import { Message } from '../types/MessageType';
import {
  getMessages,
  postMessage,
  updateMessage,
} from '../services/apiService';

export function useMessages() {
  const [messages, setMessages] = useState<Message[]>([]);

  const loadMessages = useCallback(async () => {
    try {
      const data = await getMessages();
      console.log('Message loaded');
      setMessages(data);
    } catch (error) {
      console.log(error);
    }
  }, []);

  // TODO À voir si pas mieux dans app
  useEffect(() => {
    loadMessages();
  }, [loadMessages]);

  const sendMessage = async (content: string) => {
    try {
      await postMessage(content);
      await loadMessages();
      return true;
    } catch (error) {
      console.log(error);
      return false;
    }
  };

  const editMessage = async (id: number, content: string) => {
    try {
      await updateMessage({ id: id, content: content });
      await loadMessages();
      return true;
    } catch (error) {
      console.log(error);
      return false;
    }
  };

  return { messages, sendMessage, editMessage };
}
