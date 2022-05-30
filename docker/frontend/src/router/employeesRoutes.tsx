import { Employee } from "../components/pages/EmployeeManagement";
import { EmployeeRegistry } from "../components/pages/EmployeeRegistry";
import { Page404 } from "../components/pages/Page404";

export const employeesRoutes = [
  {
    path: "/",
    exact: true,
    children: <Employee />,
  },
  {
    path: "/new",
    exact: true,
    children: <EmployeeRegistry />,
  },
  {
    path: "*",
    exact: false,
    children: <Page404 />,
  },
];
