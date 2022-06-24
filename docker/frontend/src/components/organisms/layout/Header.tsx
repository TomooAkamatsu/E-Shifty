import {
  Box,
  Flex,
  Heading,
  HStack,
  Link,
  useDisclosure,
} from "@chakra-ui/react";
import { memo, useCallback, VFC } from "react";
import { useHistory } from "react-router-dom";
import { useAuthUser } from "../../../provider/login/AuthUserContext";
import { MenuIconButton } from "../../atoms/button/MenuIconButton";
import { MenuDrawer } from "../../molecules/MenuDrawer";

export const Header: VFC = memo(() => {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const history = useHistory();
  const authUser = useAuthUser();

  const onClickShift = useCallback(
    () => history.push("/shiftwork_management/shift"),
    [history]
  );
  const onClickEmployees = useCallback(
    () => history.push("/shiftwork_management/employees"),
    [history]
  );
  const onClickLogout = useCallback(
    () => history.push("/shiftwork_management/logout"),
    [history]
  );

  return (
    <>
      <Flex
        as="nav"
        bg="#03acf5"
        color="gray.50"
        align="center"
        justify="space-between"
        padding={{ base: 3, md: 5 }}
      >
        <Flex
          align="center"
          as="a"
          mr={8}
          _hover={{ cursor: "pointer" }}
          onClick={onClickShift}
        >
          <Heading as="h1" fontSize={{ base: "md", md: "lg" }}>
            シフト管理アプリ
          </Heading>
        </Flex>
        <Flex
          align="center"
          fontSize="sm"
          flexGrow={2}
          display={{ base: "none", md: "flex" }}
        >
          <Box pr={4}>
            <Link onClick={onClickShift} fontSize={{ base: "xl", md: "md" }}>
              シフト
            </Link>
          </Box>
          <Box>
            <Link
              onClick={onClickEmployees}
              fontSize={{ base: "xl", md: "md" }}
            >
              従業員
            </Link>
          </Box>
          <HStack ml="auto">
            <Box pr="3">
              <p>{`従業員ID: ${authUser?.userId}`}</p>
            </Box>
            <Box>
              <Link onClick={onClickLogout} fontSize={{ base: "xl", md: "md" }}>
                ログアウト
              </Link>
            </Box>
          </HStack>
        </Flex>
        <MenuIconButton onOpen={onOpen} />
      </Flex>
      <MenuDrawer
        onClose={onClose}
        isOpen={isOpen}
        onClickShift={onClickShift}
        onClickEmployees={onClickEmployees}
        onClickLogout={onClickLogout}
      />
    </>
  );
});
