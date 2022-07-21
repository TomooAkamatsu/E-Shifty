import { Box, Flex } from "@chakra-ui/react";
import React from "react";
import { useHistory } from "react-router-dom";
import { PrimaryButton } from "../atoms/button/PrimaryButton";
import { useLogout } from "../../provider/login/AuthUserContext";

export const Logout = () => {
  const history = useHistory();
  const logout = useLogout();
  const onClickLogout = () => {
    history.push("/login");
    logout();
  };

  return (
    <Flex align="center" justify="center" height="100vh">
      <Box
        bg="white"
        w="sm"
        p={4}
        borderRadius="md"
        shadow="md"
        textAlign="center"
      >
        <h1 style={{ fontSize: "20px" }}>ログアウトしました！</h1>
        <PrimaryButton onClick={onClickLogout}>ログインページへ</PrimaryButton>
      </Box>
    </Flex>
  );
};
