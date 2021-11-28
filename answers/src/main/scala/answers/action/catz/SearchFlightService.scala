package answers.action.catz

import answers.action.fp.search.{Airport, Flight, SearchResult}
import cats.effect.IO
import cats.implicits._

import java.time.LocalDate
import scala.concurrent.duration._

// This represent the main API of Lambda Corp.
// `search` is called whenever a user press the "Search" button on the website.
trait SearchFlightService {
  def search(from: Airport, to: Airport, date: LocalDate): IO[SearchResult]
}

object SearchFlightService {

  def fromClients(clients: List[SearchFlightClient]): SearchFlightService =
    new SearchFlightService {
      def search(from: Airport, to: Airport, date: LocalDate): IO[SearchResult] = {
        def fetchFlights(client: SearchFlightClient): IO[List[Flight]] =
          client
            .search(from, to, date)
            .timeout(2.seconds)
            .handleErrorWith(_ => IO(Nil))
            .map(_.filter { flight =>
              flight.from == from && flight.to == to && flight.departureDate == date
            })

        clients
          .flatTraverse(fetchFlights)
          .map(SearchResult.apply)
      }
    }

}
