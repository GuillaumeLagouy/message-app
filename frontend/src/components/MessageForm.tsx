import { SubmitHandler, useForm } from 'react-hook-form';

type Inputs = {
  content: string;
};

interface MessageFormProps {
  onSendMessage: (message: string) => Promise<boolean>;
}

export default function MessageForm({ onSendMessage }: MessageFormProps) {
  const { handleSubmit, register, reset } = useForm<Inputs>();

  const onSubmit: SubmitHandler<Inputs> = async (data) => {
    const success = await onSendMessage(data.content);
    if (success) {
      reset();
    }
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <input placeholder="Write your message" {...register('content')} />
      <input type="submit" />
    </form>
  );
}
