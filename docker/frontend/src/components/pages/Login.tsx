import {
  Box,
  Center,
  HStack,
  Image,
  Input,
  Stack,
  Text,
} from "@chakra-ui/react";
import { ChangeEvent, memo, useState, VFC } from "react";
import { PrimaryButton } from "../atoms/button/PrimaryButton";
import { useGuestLogin, useLogin } from "../../provider/login/AuthUserContext";
import { DeleteButton } from "../atoms/button/DeleteButton";
import { ExplanationCard } from "../organisms/login/ExplanationCard";

type loginArgs = {
  userId: string;
  password: string;
};

export const Login: VFC = memo(() => {
  const [employeeId, setEmployeeId] = useState("");
  const [password, setPassword] = useState("");
  const login = useLogin();
  const guestLogin = useGuestLogin();

  const onClickLogin = () => {
    const loginArgs: loginArgs = { userId: employeeId, password: password };
    login(loginArgs);
  };

  const onClickGuestLogin = () => {
    guestLogin();
  };

  const onChangeEmployeeId = (e: ChangeEvent<HTMLInputElement>) => {
    setEmployeeId(e.target.value);
  };
  const onChangePassword = (e: ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  };

  return (
    <Box w="100vw" h="auto">
      <Box
        pos="absolute"
        zIndex={1}
        w="100vw"
        h={{ sm: "420px", md: "420px", lg: "420px", xl: "580px" }}
        backgroundImage="url('https://sma-image.s3.ap-northeast-1.amazonaws.com/top_image.png')"
        backgroundRepeat="no-repeat"
        backgroundSize="cover"
      ></Box>
      <Box
        zIndex={10}
        pos="relative"
        top={{ sm: "230px", md: "230px", lg: "230px", xl: "330px" }}
        left={{ sm: "220px", md: "220px", lg: "220px", xl: "410px" }}
      >
        <Stack spacing="0">
          <HStack spacing={6}>
            <Input
              placeholder="従業員ID"
              _placeholder={{
                color: "#444444",
              }}
              value={employeeId}
              onChange={onChangeEmployeeId}
              w="150px"
              borderColor="blackAlpha.800"
              bgColor="whiteAlpha.700"
            />
            <Input
              type="password"
              placeholder="パスワード"
              _placeholder={{
                color: "#444444",
              }}
              value={password}
              onChange={onChangePassword}
              w="150px"
              borderColor="blackAlpha.800"
              bgColor="whiteAlpha.700"
            />
            <Box w="100px">
              <PrimaryButton onClick={onClickLogin}>ログイン</PrimaryButton>
            </Box>
          </HStack>
          <Box pl="205px">
            <DeleteButton onClick={onClickGuestLogin}>
              ゲストログインはこちらから
            </DeleteButton>
          </Box>
        </Stack>
      </Box>
      <Box
        pos="relative"
        top={{ sm: "320px", md: "320px", lg: "320px", xl: "480px" }}
        zIndex={11}
      >
        <Box textAlign="center">
          <Text fontSize="40px" color="blue.400">
            E-Shiftyで出来ること
          </Text>
          <Box background="blue.100" m={5} borderRadius={10}>
            <Text fontSize="30px" color="blue.500" pt={5}>
              <span style={{ fontSize: "35px" }}>従業員</span>の場合
            </Text>
            <HStack
              px={10}
              py={7}
              w="100%"
              spacing={{ sm: "20px", md: "20px", lg: "20px", xl: "auto" }}
            >
              <ExplanationCard
                title="シフト確認"
                src="https://sma-image.s3.ap-northeast-1.amazonaws.com/%E3%82%B7%E3%83%95%E3%83%88%E7%A2%BA%E8%AA%8D.png"
                alt="シフト確認"
                text="作成された全従業員のシフトを、月毎に確認することができます"
              />
              <ExplanationCard
                title="休み希望の提出"
                src="https://sma-image.s3.ap-northeast-1.amazonaws.com/%E4%BC%91%E3%81%BF%E6%8F%90%E5%87%BA.png"
                alt="休み希望の提出"
                text="来月分の休み希望日程を提出することができます"
              />
              <ExplanationCard
                title="従業員一覧の確認"
                src="https://sma-image.s3.ap-northeast-1.amazonaws.com/%E5%BE%93%E6%A5%AD%E5%93%A1%E4%B8%80%E8%A6%A7.png"
                alt="従業員一覧の確認"
                text="全従業員を一目で確認でき、クリックすると詳細情報も見られます"
              />
            </HStack>
          </Box>
          <Box background="blue.100" mx={5} mb={5} mt={10} borderRadius={10}>
            <Text fontSize="30px" color="blue.400" pt={5}>
              <span style={{ fontSize: "35px" }}>管理者</span>の場合
            </Text>
            <HStack
              px={10}
              py={7}
              w="100%"
              spacing={{ sm: "20px", md: "20px", lg: "20px", xl: "auto" }}
            >
              <ExplanationCard
                title="シフト作成"
                src="https://sma-image.s3.ap-northeast-1.amazonaws.com/%E3%82%B7%E3%83%95%E3%83%88%E4%BD%9C%E6%88%90.png"
                alt="シフト作成"
                text="提出された休み希望をもとに、来月のシフトを自動で作成して修正できます"
              />
              <ExplanationCard
                title="休み希望一覧の確認"
                src="https://sma-image.s3.ap-northeast-1.amazonaws.com/%E4%BC%91%E3%81%BF%E5%B8%8C%E6%9C%9B%E4%B8%80%E8%A6%A7.png"
                alt="休み希望一覧の確認"
                text="提出された全従業員の休み希望を確認できます"
              />
              <ExplanationCard
                title="従業員情報の管理"
                src="https://sma-image.s3.ap-northeast-1.amazonaws.com/%E5%BE%93%E6%A5%AD%E5%93%A1%E8%A9%B3%E7%B4%B0.png"
                alt="従業員情報の管理"
                text="従業員情報の修正や、従業員の新規登録を行うことができます"
              />
            </HStack>
          </Box>
        </Box>
        <Text fontSize="35px" color="blue.400" textAlign="center" pt={5}>
          さあ、ラクなシフト管理をはじめよう！
        </Text>
        <Center pb={10}>
          <HStack spacing={6}>
            <Input
              placeholder="従業員ID"
              _placeholder={{
                color: "#444444",
              }}
              value={employeeId}
              onChange={onChangeEmployeeId}
              w="150px"
              borderColor="blackAlpha.800"
              bgColor="whiteAlpha.700"
            />
            <Input
              type="password"
              placeholder="パスワード"
              _placeholder={{
                color: "#444444",
              }}
              value={password}
              onChange={onChangePassword}
              w="150px"
              borderColor="blackAlpha.800"
              bgColor="whiteAlpha.700"
            />
            <Box w="100px">
              <PrimaryButton onClick={onClickLogin}>ログイン</PrimaryButton>
            </Box>
            <Box pl="50px">
              <DeleteButton onClick={onClickGuestLogin}>
                ゲストログインはこちらから
              </DeleteButton>
            </Box>
          </HStack>
        </Center>
        <Center h="100px" bg="blue.100">
          <Image
            src="https://sma-image.s3.ap-northeast-1.amazonaws.com/E-Shifty.png"
            alt="E-Shifty"
            h="50px"
          />
        </Center>
      </Box>
    </Box>
  );
});
