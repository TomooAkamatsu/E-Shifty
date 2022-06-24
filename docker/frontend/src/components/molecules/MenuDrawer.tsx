import {
  Box,
  Button,
  Drawer,
  DrawerBody,
  DrawerContent,
  DrawerOverlay,
} from "@chakra-ui/react";
import { memo, VFC } from "react";
import { useAuthUser } from "../../provider/login/AuthUserContext";

type Props = {
  onClose: () => void;
  isOpen: boolean;
  onClickShift: () => void;
  onClickEmployees: () => void;
  onClickLogout: () => void;
};

export const MenuDrawer: VFC<Props> = memo((props) => {
  const authUser = useAuthUser();
  const { onClose, isOpen, onClickShift, onClickEmployees, onClickLogout } =
    props;
  return (
    <Drawer placement="left" size="xs" onClose={onClose} isOpen={isOpen}>
      <DrawerOverlay>
        <DrawerContent>
          <DrawerBody p={0} bg="gray.100" textAlign="center">
            <Box py={3}>
              <p>{`従業員ID: ${authUser?.userId}`}</p>
            </Box>
            <Button w="100%" onClick={onClickShift}>
              シフト
            </Button>
            <Button w="100%" onClick={onClickEmployees}>
              従業員
            </Button>
            <Button w="100%" onClick={onClickLogout}>
              ログアウト
            </Button>
          </DrawerBody>
        </DrawerContent>
      </DrawerOverlay>
    </Drawer>
  );
});
