import { SubmitHandler, useForm } from 'react-hook-form';
import { postMessage } from '../services/apiService';

type Inputs = {
  content: string;
};

export default function MessageForm() {
  const { handleSubmit, register } = useForm<Inputs>();
  const onSubmit: SubmitHandler<Inputs> = async (data) => {
    try {
      const result = await postMessage(data);
      console.log('Message sent:', result);
    } catch (error) {
      console.error('Error sending message:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <input placeholder="Write your message" {...register('content')} />
      <input type="submit" />
    </form>
  );
}
