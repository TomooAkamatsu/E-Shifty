import { Button } from "@chakra-ui/react";
import { memo, ReactNode, VFC } from "react";

type Props = {
  children: ReactNode;
  onClick: () => void;
};

export const DeleteButton: VFC<Props> = memo((props) => {
  const { children, onClick } = props;
  return (
    <Button
      bg="#f50303"
      color="white"
      _hover={{ opacity: 0.8 }}
      onClick={onClick}
      m={3}
    >
      {children}
    </Button>
  );
});
