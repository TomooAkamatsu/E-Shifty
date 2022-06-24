import { Box, Divider, Flex, Heading, Input, Stack } from "@chakra-ui/react";
import { ChangeEvent, memo, useState, VFC } from "react";
import { PrimaryButton } from "../atoms/button/PrimaryButton";
import { useLogin } from "../../provider/login/AuthUserContext";

export const Login: VFC = memo(() => {
  const [employeeId, setEmployeeId] = useState("");
  const [password, setPassword] = useState("");
  const login = useLogin();

  const onClickLogin = () => {
    login(employeeId);
  };

  const onChangeEmployeeId = (e: ChangeEvent<HTMLInputElement>) => {
    setEmployeeId(e.target.value);
  };
  const onChangePassword = (e: ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  };

  return (
    <Flex align="center" justify="center" height="100vh">
      <Box bg="white" w="sm" p={4} borderRadius="md" shadow="md">
        <Heading as="h1" size="lg" textAlign="center">
          シフト管理アプリ
        </Heading>
        <Divider my={4} />
        <Stack spacing={6} py={4} px={10}>
          <Input
            placeholder="ユーザーID"
            value={employeeId}
            onChange={onChangeEmployeeId}
          />
          <Input
            type="password"
            placeholder="パスワード"
            value={password}
            onChange={onChangePassword}
          />
          <PrimaryButton onClick={onClickLogin}>ログイン</PrimaryButton>
        </Stack>
      </Box>
    </Flex>
  );
});
