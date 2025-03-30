import { Card, Text } from "react-native-paper";
import Layout from "../components/Layout";
import { useEffect, useState } from "react";
import { getNews } from "../modules/news/api";
import { NewsInterface } from "../modules/news/interfaces";
import { ActivityIndicator, ScrollView, StyleSheet, View } from "react-native";
import RenderHtml from "react-native-render-html";
import { formatDate } from "../utils/date";

const NewsTab = () => {
  const [data, setData] = useState<NewsInterface[]>([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    setIsLoading(true);
    try {
      getNews()
        .then((res) => {
          setData(res.data.content);
        })
        .finally(() => setIsLoading(false));
    } catch (e) {
      console.error(e);
    }
  }, []);

  // useEffect(() => {
  //   let isMounted = true;
  //   let isRequestInProgress = false;
  //
  //   const fetchData = async () => {
  //     if (isRequestInProgress || !isMounted) return;
  //
  //     isRequestInProgress = true;
  //     setIsLoading(true);
  //
  //     try {
  //       const response = await getNews();
  //       if (isMounted) {
  //         setData(response.data.content);
  //       }
  //     } catch (error) {
  //       console.error(error);
  //     } finally {
  //       if (isMounted) {
  //         setIsLoading(false);
  //       }
  //       isRequestInProgress = false;
  //     }
  //   };
  //   fetchData();
  //
  //   const interval = setInterval(fetchData, 5000);
  //
  //   return () => {
  //     isMounted = false;
  //     clearInterval(interval);
  //   };
  // }, []);

  if (isLoading) {
    return (
      <Layout>
        <ActivityIndicator animating={true} />
      </Layout>
    );
  }

  return (
    <ScrollView contentContainerStyle={styles.view}>
      {data.map((item) => (
        <Card mode={"elevated"} style={styles.card} key={item.id}>
          <View style={styles.cardHeader}>
            <Text style={styles.cardTitle}>{item.title}</Text>
            <Text>{formatDate(item.creationDate)}</Text>
          </View>
          <RenderHtml source={{ html: item.content }} />
        </Card>
      ))}
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  view: {
    paddingTop: 16,
    paddingBottom: 16,
    display: "flex",
    flexDirection: "column",
    gap: 16,
  },
  card: {
    padding: 16,
  },
  cardHeader: {
    display: "flex",
    flexDirection: "row",
    justifyContent: "space-between",
  },
  cardTitle: {
    fontSize: 16,
    fontWeight: 500,
  },
});

export default NewsTab;
