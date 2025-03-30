"use client";

import { useEffect, useState } from "react";
import { getApplications } from "@/modules/applications/api";
import { ApplicationInterface } from "@/modules/applications/interfaces";
import ApplicationsTable from "@/modules/applications/components/ApplicationsTable";
import { CategoryInterface } from "@/interfaces/category";
import { getCategories } from "@/api";

const ApplicationsPage = () => {
  const [applications, setApplications] = useState<ApplicationInterface[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [categories, setCategories] = useState<CategoryInterface[]>([]);
  const [isLoadingCategories, setIsLoadingCategories] = useState(true);

  useEffect(() => {
    setIsLoadingCategories(true);
    try {
      getCategories().then((res) => {
        setCategories(res.data.values as CategoryInterface[]);
      });
    } catch (e) {
      console.log(e);
    } finally {
      setIsLoadingCategories(false);
    }
  }, []);

  useEffect(() => {
    setIsLoading(true);
    try {
      getApplications().then((res) => {
        setApplications(res.data.content as ApplicationInterface[]);
      });
    } catch (e) {
      console.log(e);
    } finally {
      setIsLoading(false);
    }
  }, []);

  if (isLoading || isLoadingCategories) return <div>Loading...</div>;

  return <ApplicationsTable data={applications} categories={categories} />;
};

export default ApplicationsPage;
