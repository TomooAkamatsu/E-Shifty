import { Page404 } from "../components/pages/Page404";
import { VacationRequest } from "../components/pages/VacationRequest";
import { VacationRequestList } from "../components/pages/VacationRequestList";
import { ShiftCreation } from "../components/pages/ShiftCreation";
import { Shift } from "../components/pages/Shift";

export const shiftRoutes = [
  {
    path: "/",
    exact: true,
    children: <Shift />,
  },
  {
    path: "/new",
    exact: true,
    children: <ShiftCreation />,
  },
  {
    path: "/request",
    exact: true,
    children: <VacationRequest />,
  },
  {
    path: "/request/list",
    exact: true,
    children: <VacationRequestList />,
  },
  {
    path: "*",
    exact: false,
    children: <Page404 />,
  },
];
