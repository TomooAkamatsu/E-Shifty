import { Page404 } from "../components/pages/Page404";
import { Request } from "../components/pages/Request";
import { RequestList } from "../components/pages/RequestList";
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
    children: <Request />,
  },
  {
    path: "/request/list",
    exact: true,
    children: <RequestList />,
  },
  {
    path: "*",
    exact: false,
    children: <Page404 />,
  },
];
