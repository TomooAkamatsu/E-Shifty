import { Box, Image, Stack, Text } from "@chakra-ui/react";
import { memo, VFC } from "react";

type Props = {
  title: string;
  src: string;
  alt: string;
  text: string;
};

export const ExplanationCard: VFC<Props> = memo((props) => {
  const { title, src, alt, text } = props;

  return (
    <Box
      h="430px"
      w="360px"
      background="gray.100"
      borderRadius={10}
      shadow="md"
    >
      <Stack>
        <Text pt={5} fontSize="30px" color="blue.500">
          {title}
        </Text>
        <Image src={src} alt={alt} px={2} pb={2} />
        <Text textAlign="left" px={5} fontSize="18px" color="blue.600">
          {text}
        </Text>
      </Stack>
    </Box>
  );
});
