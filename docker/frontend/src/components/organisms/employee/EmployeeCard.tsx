import { Box, Stack, Text } from "@chakra-ui/react";
import { memo, VFC } from "react";

type Props = {
  employeeId: number;
  lastName: string;
  firstName: string;
  romanLastName: string;
  romanFirstName: string;
  phoneNumber: string;
  email: string;
  workingForm: string;
};

export const EmployeeCard: VFC<Props> = memo((props) => {
  const {
    employeeId,
    lastName,
    firstName,
    romanLastName,
    romanFirstName,
    phoneNumber,
    email,
    workingForm,
  } = props;

  return (
    <Box
      w="350px"
      h="220px"
      bg="white"
      borderRadius="10px"
      shadow="md"
      p={8}
      _hover={{ cursor: "pointer", opacity: 0.8 }}
    >
      <Stack>
        <Text>従業員No: {employeeId}</Text>
        <Text>
          <span style={{ fontSize: "22px" }}>
            {lastName}&nbsp;{firstName}
          </span>
          &emsp;
          <span style={{ fontSize: "15px" }}>
            {romanLastName}&nbsp;{romanFirstName}
          </span>
        </Text>
        <Text>TEL: {phoneNumber}</Text>
        <Text>Email: {email}</Text>
        <Text>雇用形態: {workingForm}</Text>
      </Stack>
    </Box>
  );
});
